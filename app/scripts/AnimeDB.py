import json
import pymongo
import pandas as pd

#Deprecated

'''
mng_client = pymongo.MongoClient('localhost', 27017)
mng_db = mng_client['anime_db'] 
anime_collection = 'anime'
db_cm = mng_db[anime_collection]
file = 'datasets/anime.csv'
data = pd.read_csv(file)
data_json = json.loads(data.to_json(orient='records'))
db_cm.remove()
db_cm.insert(data_json)

ratings_collection = 'ratings'
db_cm = mng_db[ratings_collection]
file = 'datasets/rating.csv'
data = pd.read_csv(file)
data_json = json.loads(data.to_json(orient='records'))
db_cm.remove()
db_cm.insert(data_json)
'''