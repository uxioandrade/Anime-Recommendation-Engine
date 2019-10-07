# Anime Recommendation Engine

> The purpose of this project is to implement different recommendation algorithms and integrate them in a web app.
 
## Short Demo

![Gif](https://media.giphy.com/media/d9O8doAiylGW9jvgEM/giphy.gif)

## Version Summary

* [Play Framework: 2.7.2](https://www.playframework.com/documentation/2.7.x/Home)
* [React: 16.8.6](https://reactjs.org/)
* [Create React App: 2.1.8](https://github.com/facebookincubator/create-react-app)
* [Spark: 2.4.0](https://spark.apache.org)
* [PostgreSQL: 9.4](https://www.postgresql.org)
* [Python 3.X](https://www.python.org)

## Setting up

### Prerequisites

* [Node.js](https://nodejs.org/)
* [scala](https://www.scala-lang.org/download/)
* [Docker](https://www.docker.com)

### Getting started

# First, you need to navigate to the following datasets:
* [dataset](https://www.kaggle.com/azathoth42/myanimelist) And download the files `anime_cleaned.csv`,`animelists_cleaned` and `users_cleaned`
* Save the datasets in a directory named `datasets` in the root directory
* Now run the scripts `dataset_cleaner.py` and then `clean_images.py` using python3
* Execute the `init_db_docker.sh` file. Uncomment the first lines if you don't have the necessary docker images
* At this point, you can execute ``sbt run``, which will trigger ``npm run start``
* Finally, enter the sbt console and run ``util.RecommendationModel``, which will save the ALS model.


## License

This software is licensed under the MIT license
