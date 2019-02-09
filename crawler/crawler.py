import bs4
import csv
from pathlib import Path
import functions as f

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36',
}

def scrape_cuisines():

    res = requests.get("https://www.bbc.com/food/cuisines", headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    cuisines = soup.find_all(
        'div', attrs={'class:', 'gel-layout__item promo__title-container'})
    for c in cuisines:
        title = c.find('h3', attrs={'class:', 'promo__title gel-pica'}).text
        scrape_cuisine(title)

        


def scrape_cuisine(title):
    url = "https://www.bbc.com/food/cuisines/{0}".format(title)
    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    #Do check for validity
    all_tag = soup.find('a', attrs={
                        'class:', 'gel-pica featured-az-switcher__link featured-az-switcher__link--all'})
    
    if(all_tag == None):
        return None
    
    #If we get here, then the cuisine is valid (has enough recipes)
    scrape_recipies("www.bbc.com{0}".format(all_tag['href']))

def scrape_recipies(url):

    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    index = 0

    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    all_recipies = soup.find_all(
        'div', attrs={'class:', 'gel-layout__item gel-1/2 gel-1/3@m gel-1/4@xl'})

    for recipie in all_recipies:
        href = recipie.find(
            'a', attrs={'class:', 'promo promo__istats promo__brunch'})['href']
        scrape_recipie("www.bbc.com{0}".format(href))
        
def scrape_recipie(url):

    data_row = []

    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    prep_time = soup.find('p', attrs={'class:', 'recipe-metadata__prep-time'}).text
    cook_time = soup.find('p', attrs={'class:', 'recipe-metadata__cook-time'}).text
    is_veg = soup.find('p', attrs={'class:', 'recipe-metadata__dietary-vegetarian-text'}) != None

    ingredients = soup.find_all('li', attrs={'recipe-ingredients__list-item'})

    ingredient_list = []
    steps_list = []

    for ingredient in ingredients:
        amount = ingredient.text
        name = ingredient.find('a').text
        ingredient_list.append(amount + name)


    ingredients_string = ""
    for gred in ingredient_list:
        ingredients_string += gred +" "

    steps = soup.find_all(
        'p', attrs={'class:', 'recipe-method__list-item-text'})

    for step in steps:
        steps_list.append(step.text)

    img = soup.find('img', attrs={'class:', 'recipe-media__image'})

    img_src = img['src']

    data_row = [title, None, f.getTime(prep_time, cook_time), int(is_veg), f.getMeats(ingredients_string), ]



def fileExists(file_name, directory):
    if('.' not in file_name):
        file_name += '.csv'
    return Path(directory, file_name).is_file()

def filesExist(file_names, directory):
    for file_name in file_names:
        if(not fileExists(file_name, directory)):
            return False
    return True

def dataToCsv(data, file_path, file_name, header=None, overwrite=True):
    '''
        (list of str, str) -> None
        Precondition: file_name contains extension
        Writes the given data to a csv file of name file_name
    '''

    method = 'w'
    if(not overwrite):
        method = 'a'

    file_exists = fileExists(file_name, file_path)

    with open(file_path + file_name, method) as csv_file:
        writer = csv.writer(csv_file, lineterminator='\n')
        if(not file_exists and not overwrite):
            writer.writerow(header)
        elif(overwrite):
            data = [header] + data
        writer.writerows(data)






