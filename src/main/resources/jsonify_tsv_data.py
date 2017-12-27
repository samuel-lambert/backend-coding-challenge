#!/usr/bin/env python3

'''
Parses the TSV data then generates a JSON file only
containing the data needed for auto-completion.

This relevant data is:
- City name
- Alternate names
- State/province
- Country
- Latitude
- Longitude

The format on the TSV file is the following:
id name ascii alt_name lat long feat_class feat_code country ...
'''

import json

INPUT_FILE = 'cities_canada-usa.tsv'
OUTPUT_FILE = 'cities_canada-usa.json'


def sanitize_alternate_names(alternate_names):
    '''
    Returns a list of alternate city names according to the
    given CSV string.

    It appears that tokens starting with a space is an alternate
    name for the city's state/province. I am not sure why those
    were included in the first place but I am gladly omiting them!
    '''

    tokens = alternate_names.split(',')
    return list(filter(lambda x: x and not x.startswith(' '), tokens))


def resolve_state(token, country):
    '''
    Retrieves the two-letter state/province code, considering
    that provinces are numerically coded.

    For some reason, code 06 is not being used.
    '''
    if country == 'us':
        return token.lower()

    if token == '01':
        return 'ab'
    elif token == '02':
        return 'bc'
    elif token == '03':
        return 'mb'
    elif token == '04':
        return 'nb'
    elif token == '05':
        return 'nl'
    elif token == '07':
        return 'ns'
    elif token == '08':
        return 'on'
    elif token == '09':
        return 'pe'
    elif token == '10':
        return 'qc'
    elif token == '11':
        return 'sk'
    elif token == '12':
        return 'yt'
    elif token == '13':
        return 'nt'
    elif token == '14':
        return 'nu'
    else:
        raise ValueError('Unknown state/province value')


def main():
    cities_list = []

    with open(INPUT_FILE) as input_file:
        next(input_file)  # skipping header

        for line in input_file:
            tokens = line.split('\t')

            name = tokens[1].lower()
            alternate_names = tokens[3].lower()
            country = tokens[8].lower()
            state = resolve_state(tokens[10], country)
            latitude = float(tokens[4])
            longitude = float(tokens[5])

            city = {
                'name': name,
                'alternate_names': sanitize_alternate_names(alternate_names),
                'state': state,
                'country': country,
                'latitude': latitude,
                'longitude': longitude
            }

            cities_list.append(city)

    with open(OUTPUT_FILE, 'w') as output_file:
        json.dump(cities_list, output_file, ensure_ascii=False)


if __name__ == '__main__':
    main()
