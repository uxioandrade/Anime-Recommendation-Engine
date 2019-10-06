import json
import pandas as pd

file = '../../datasets/animelist_clean.csv'
ratingsDF = pd.read_csv(file)

file = '../../datasets/users_cleaned.csv'
usersDF = pd.read_csv(file)

ratingsDF['user_id'] = 0

print(ratingsDF.shape[0])
for index,row in ratingsDF.iterrows():
    value = usersDF[usersDF['username'] == row['username']]
    #print(value['user_id'])
    ratingsDF.at[index,'user_id'] = value['user_id']
    print(str(index) + " out of " + str(ratingsDF.shape[0]))
print(usersDF.head(5))
print(ratingsDF.head(5))
ratingsDF.to_csv ('../../datasets/ratings_clean.csv', index = None, header=True)


