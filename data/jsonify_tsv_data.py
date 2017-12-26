#!/usr/bin/env python3

import json

INPUT_FILE = 'cities_canada-usa.tsv'
OUTPUT_FILE = 'cities_canada-usa.json'

def main():
    '''
    This parses the TSV data then generates a JSON file
    only containing the data needed for auto-completion.

    This relevant data is: 
    - City name
    - Country
    - Latitude
    - Longitude
    '''

    cities_list = []

    with open(INPUT_FILE) as input_file:
        next(input_file) # skipping header

        for line in input_file:
            tokens = line.split('\t')
            city = {
                'name': tokens[1].lower(),
                'country': tokens[8].lower(),
                'lat': float(tokens[4]),
                'long': float(tokens[5])
            }

            cities_list.append(city)

    with open(OUTPUT_FILE, 'w') as output_file:
        json.dump(cities_list, output_file, ensure_ascii=False)

if __name__ == '__main__':
    main()
