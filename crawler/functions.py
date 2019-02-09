import re

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

print(getTime("30 mins to 1 hour", "1 to 1 mins"));
