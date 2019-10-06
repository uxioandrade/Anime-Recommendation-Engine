import json
import pandas as pd

file = 'datasets/anime_cleaned.csv'
animeDF = pd.read_csv(file)

for index,row in animeDF.iterrows():
    try:
        value = row['image_url']
        a = value.split(".")
        a.remove('cdn-dena')

        c = a[0][:8]+'cdn.'+a[0][8:] + '.' + ".".join(a[1:])

        print(c)
        animeDF.at[index,'image_url'] = c
        print(str(index) + " out of " + str(animeDF.shape[0]))
    except:
        print('error in image')

animeDF.to_csv ('datasets/anime_cleaned.csv', index = None, header=True)