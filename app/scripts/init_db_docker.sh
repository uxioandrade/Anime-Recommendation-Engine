#docker pull postgres
#docker pull dpage/pgadmin4

docker network create --driver bridge postgres-network

docker run --name anime-db --network=postgres-network -e "POSTGRES_PASSWORD=admin" -p 5432:5432 -v "./PostgreSQL:/var/lib/postgresql/data" -d postgres

docker run --name anime-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=uxiog21@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=admin" -d dpage/pgadmin4

docker cp datasets/anime_cleaned.csv anime-db:/docker-entrypoint-initdb.d/anime_cleaned.csv
docker cp datasets/users_cleaned.csv anime-db:/docker-entrypoint-initdb.d/users_cleaned.csv

docker cp app/scripts/ddl/1.sql anime-db:/docker-entrypoint-initdb.d/1.sql

docker exec -it anime-db psql -U postgres -f docker-entrypoint-initdb.d/1.sql