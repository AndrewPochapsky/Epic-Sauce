from pathlib import Path
import csv

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
