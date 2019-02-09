import re
import json

def getTime(prep, cook):
    prepTime = re.findall(r"(\b[1-9]\b|\b[1-9][0-9]\b).((\bhour\b|\bhours\b)|(\bmin\b|\bmins\b))", prep);
    prepTime = prepTime[len(prepTime)-1];
    cookTime = re.findall(r"(\b[1-9]\b|\b[1-9][0-9]\b).((\bhour\b|\bhours\b)|(\bmin\b|\bmins\b))", cook);
    cookTime = cookTime[len(cookTime)-1];

    hours = 0;
    mins = 0;

    if(prepTime[1] == "hours" or prepTime[1] == "hour") :
        hours += int(prepTime[0]);

    if(prepTime[1] == "min" or prepTime[1] == "mins") :
        mins += int(prepTime[0]);

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

    return json.dumps(meats);

def getVegetables(ingredients):
    vegetables = re.findall(r"(artichoke|aubergine|asparagus|legumes|alfalfasprouts|azukibeans|beansprouts|blackbeans|black-eyedpeas|borlottibean|broadbeans|chickpeas|greenbeans|kidneybeans|lentils|limabeansorbutterbean|mungbeans|navybeans|pintobeans|runnerbeans|splitpeas|soybeans|peas|mangetout|snappeas|broccoflower|broccoli|brusselssprouts|cabbage|kohlrabi|cauliflower|celery|endive|fiddleheads|frisee|fennel|greens|beetgreens|bokchoy|chard|collardgreens|kale|mustardgreens|spinach|herbsandspices|anise|basil|caraway|cilantro|chamomile|dill|fennel|lavender|lemongrass|marjoram|oregano|parsley|rosemary|sage|thyme|lettuce|arugula|mushrooms|nettles|spinach|okra|onions|chives|garlic|leek|onion|shallot|scallion|parsley|peppers|bellpepper|chilipepper|jalapeño|habanero|paprika|tabascopepper|cayennepepper|radicchio|rhubarb|beetroot|mangel-wurzel|carrot|celeriac|corms|eddoe|konjac|taro|waterchestnut|ginger|parsnip|rutabaga|radish|wasabi|horseradish|whiteradish|daikon|tubers|jicama|jerusalemartichoke|potato|quandong|sunchokes|sweetpotato|yam|turnip|salsify|skirret|sweetcorn|topinambur|acornsquash|bittermelon|butternutsquash|bananasquash|courgette|cucumber|delicata|gemsquash|hubbardsquash|marrow|pattypans|pumpkin|spaghettisquash|tatsoi|tomato|watercress)", ingredients, flags=re.IGNORECASE);

    return json.dumps(vegetables);

def isSpicy(ingredients):
    spice = re.findall(r"(chili|jalapeno|peppers|sriracha|tabasco)", ingredients, flags=re.IGNORECASE);
    if(len(spice) > 0):
        return 1;
    else:
        return 0;
