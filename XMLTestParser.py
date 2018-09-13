import sys
import os
import csv
import xml.etree.ElementTree as ET

path = os.path.dirname(os.path.realpath(__file__)) + "/" + sys.argv[1]


for file in os.listdir(path):
    if file.endswith(".xml"): 
        filepath = os.path.join(path, file)
        filname_split = file.split("_")
        testcase = filname_split[len(filname_split)-1].split(".")[0]


        if not os.path.exists(os.path.join(path, "formatted")):
            os.makedirs(os.path.join(path, "formatted"))

        
        with open(str(os.path.join(path, "formatted") + "/" + file + "-formatted.csv"), 'wb') as csvfile:
            spamwriter = csv.writer(csvfile, delimiter=',', quotechar='|', quoting=csv.QUOTE_MINIMAL)
        
            tree = ET.parse(filepath)
            root = tree.getroot()

            for sf in root.iter('sourcefile'):
                for line in sf:
                    if line.tag == "line" and int(line.attrib['ci']) > 0:
                        spamwriter.writerow([testcase, sf.attrib['name'].split(".")[0], line.attrib['nr']])
        
        continue
    else:
        continue
