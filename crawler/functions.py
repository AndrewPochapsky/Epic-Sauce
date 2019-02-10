import re

import json


def getTime(prep, cook):
    prepTime = re.findall(r"(\b[1-9]\b|\b[1-9][0-9]\b).((\bhour\b|\bhours\b)|(\bmin\b|\bmins\b))", prep);
    if(len(prepTime) != 0):
        prepTime = prepTime[len(prepTime)-1];
    cookTime = re.findall(r"(\b[1-9]\b|\b[1-9][0-9]\b).((\bhour\b|\bhours\b)|(\bmin\b|\bmins\b))", cook);
    if(len(cookTime) != 0):
        cookTime = cookTime[len(cookTime)-1];

    hours = 0;
    mins = 0;
    if(len(prepTime) != 0):
        if(prepTime[1] == "hours" or prepTime[1] == "hour") :
            hours += int(prepTime[0]);

        if(prepTime[1] == "min" or prepTime[1] == "mins") :
            mins += int(prepTime[0]);

    if(len(cookTime) != 0):
        if(cookTime[1] == "hours" or cookTime[1] == "hour") :
            hours += int(cookTime[0]);

        if(cookTime[1] == "min" or cookTime[1] == "mins") :
            mins += int(cookTime[0]);

    hours += int(mins/60);
    mins = mins%60;

    output = "";

    if(hours != 0):
        if(hours != 1):
            output = output + str(hours)+" hours ";
        else:
            output = output + str(hours)+" hour ";
    if (mins != 0) :
        if(mins != 1):
            output = " "+output + str(mins)+" minutes";
        else:
            output = " "+output + str(mins)+" minute";

    output = output.strip();

    return output;

def getMeats(ingredients):
    meats = re.findall(r"(anchovies|sausage|beef|pork|pancetta|ham|salami|chicken|bison|veal|goat|salmon|tuna|shrimp|tilapia|halibut|cod|sole|perch|catfish|trout|lobster|crab|bacon)", ingredients, flags=re.IGNORECASE);
    meats = set(meats)
    meats = list(meats)
    return json.dumps(meats);

def getVegetables(ingredients):
    vegetables = re.findall(r"(artichoke|aubergine|asparagus|legumes|alfalfa sprouts|azuki bean|beansprout|black bean|black-eyed peas|borlotti bean|broad beans|chick pea|green bean|kidneybean|lentil|lima bean|mung bean|navy bean|pinto bean|runne rbean|split pea|soy bean|pea|mangetout|snap pea|broccoflower|broccoli|brussels sprout|cabbage|kohlrabi|cauliflower|celery|endive|fiddlehead|frisee|fennel|greens|beetgreens|bokchoy|chard|collard green|kale|mustard green|spinach|herbs and spices|anise|basil|caraway|cilantro|chamomile|dill|fennel|lavender|lemongrass|marjoram|oregano|parsley|rosemary|sage|thyme|lettuce|arugula|mushrooms|nettles|spinach|okra|onions|chives|garlic|leek|onion|shallot|scallion|parsley|pepper|chili pepper|jalapeño|habanero|paprika|tabasco pepper|cayenne pepper|radicchio|rhubarb|beetroot|mangel-wurzel|carrot|celeriac|corms|eddoe|konjac|taro|waterchestnut|ginger|parsnip|rutabaga|radish|wasabi|horseradish|whiteradish|daikon|tubers|jicama|jerusalemartichoke|potato|quandong|sunchokes|sweet potato|yam|turnip|salsify|skirret|sweetcorn|topinambur|acorn squash|bitter melon|butternut squash|banana squash|courgette|cucumber|delicata|gem squash|hubbard squash|marrow|pattypans|pumpkin|spaghetti squash|tatsoi|tomato|watercress)", ingredients, flags=re.IGNORECASE);
    vegetables = set(vegetables)
    vegetables = list(vegetables)
    return json.dumps(vegetables);

def isSpicy(ingredients):
    spice = re.findall(r"(chili|jalapeno|peppers|sriracha|tabasco)", ingredients, flags=re.IGNORECASE);
    if(len(spice) > 0):
        return 1;
    else:
        return 0;
