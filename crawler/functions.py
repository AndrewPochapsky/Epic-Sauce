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

print(getMeats("1 large shallot, roughly chopped 6 anchovies, roughly chopped 1 tsp Dijon mustard 2 tbsp roughly chopped mint leaves, stalks reserved 2 tbsp roughly chopped dill, stalks reserved 2 tbsp roughly chopped tarragon, stalks reserved 2 tbsp roughly chopped flatleaf parsley, stalks reserved 2 tbsp roughly chopped watercress, stalks reserved 2 tbsp torn fresh basil 4 tbsp extra virgin olive oil, plus extra for drizzling 2 tbsp capers, drained 3 lemons, halved 1.5kg/3lb 5oz whole trout, cleaned and gutted 400g/14oz new potatoes, par boiled sea salt and freshly ground black pepper"));
