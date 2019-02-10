import bs4

import requests
import utils
import functions as f
import json

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
        new_title = ""
        for word in title.split(" "):
            new_title += word + '_'

        new_title = new_title[0 : len(new_title) - 1]

        print("starting to scrape " + new_title)
        rows = scrape_cuisine(new_title)
        if(rows != None):
            utils.dataToCsv(rows, 'output/', 'data.csv', [], overwrite=False)

        


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
    
    rows = scrape_recipies(
        "https://www.bbc.com{0}".format(all_tag['href']), title)
    
    return rows

def scrape_recipies(url, cuisine):
    rows = []

    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    az_boxes = soup.find_all('li', attrs={
                             'class:', 'az-keyboard__list-item gel-layout__item gel-1/12@l gel-2/12@s gel-2/12'})
    for box in az_boxes:
        
        if(box.find('span', attrs={'class:', 'az-keyboard__link az-keyboard__link--disabled gel-pica-bold'}) == None):

            print(box)


            res = requests.get("https://www.bbc.com" + box.find('a')['href'], headers=headers)
            res.raise_for_status()
            soup = bs4.BeautifulSoup(res.text, 'html.parser')

            container = soup.find(
                'div', attrs={'class:', 'gel-wrap promo-collection__container cuisine-page'})

            all_recipies = container.find_all(
                'div', attrs={'class:', 'gel-layout__item gel-1/2 gel-1/3@m gel-1/4@xl'})
            
            for recipie in all_recipies:
                href = recipie.find('a')['href']
                row = scrape_recipie("https://www.bbc.com{0}".format(href), cuisine)
                rows.append(row)
            
    return rows
        
       
def scrape_recipie(url, cuisine):

    data_row = []

    res = requests.get(url, headers=headers)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html.parser')

    title = soup.find(
        'h1', attrs={'class:', 'gel-trafalgar content-title__text'}).text

    prep_time = soup.find('p', attrs={'class:', 'recipe-metadata__prep-time'}).text
    cook_time = soup.find('p', attrs={'class:', 'recipe-metadata__cook-time'}).text
    is_veg = soup.find('p', attrs={'class:', 'recipe-metadata__dietary-vegetarian-text'}) != None

    ingredients = soup.find_all('li', attrs={'recipe-ingredients__list-item'})

    ingredient_list = []
    steps_list = []

   

    for ingredient in ingredients:
        
        amount = ingredient.text
        if(ingredient.find('a') != None):
            #name = ingredient.find('a').text
            ingredient_list.append(amount)
        else:
            ingredient_list.append(amount)


    ingredients_string = ""
    for gred in ingredient_list:
        ingredients_string += gred +" "
    
    steps = soup.find_all(
        'p', attrs={'class:', 'recipe-method__list-item-text'})

    for step in steps:
        steps_list.append(step.text)

    img = soup.find('img', attrs={'class:', 'recipe-media__image'})

    if(img != None):
        img_src = img['src']
    else:
        img_src = None

    data_row = [title, cuisine, f.getTime(prep_time, cook_time), int(is_veg), f.getMeats(ingredients_string),
        f.isSpicy(ingredients_string), f.getVegetables(ingredients_string), img_src, json.dumps(ingredient_list), json.dumps(steps_list)]

    return data_row

scrape_cuisines()


