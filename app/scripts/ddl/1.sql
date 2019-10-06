CREATE DATABASE animelist WITH OWNER = "postgres" ENCODING 'UTF8';
 
GRANT ALL PRIVILEGES ON DATABASE animelist to "postgres";
 
\connect animelist postgres

DROP TABLE AnimeUser;

DROP TABLE Viewer;

DROP TABLE Anime;

DROP TYPE gender;

CREATE TYPE animetype AS
    ENUM('Movie','Special', 'TV', 'Music', 'ONA', 'OVA', 'Other');

CREATE TYPE animestatus AS
    ENUM('Finished Airing','Currently Airing', 'Not yet aired', 'Other');

CREATE TYPE gender AS
    ENUM('Male','Female','Non-Binary','Other');

CREATE TABLE Anime (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(100),
    imageurl VARCHAR(100),
    format animetype,
    source VARCHAR(20),
    episodes INT CHECK(episodes >= 0),
    status animestatus,
    airing BOOLEAN,
    aired VARCHAR(500),
    duration VARCHAR(50),
    rating VARCHAR(60),
    score REAL,
    scoredby BIGINT,
    rank REAL,
    popularity BIGINT,
    members BIGINT,
    genre VARCHAR(500)
);

CREATE TABLE Viewer (
  id BIGINT NOT NULL,
  username VARCHAR(30) NOT NULL,
  userwatching INT DEFAULT 0,
  usercompleted INT DEFAULT 0,
  useronhold INT DEFAULT 0,
  userdropped INT DEFAULT 0,
  userplantowatch INT DEFAULT 0,
  userdaysspentwatching REAL,
  gender gender,
  location VARCHAR(50),
  statsepisodes BIGINT,
  FOREIGN KEY (userwatching) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (usercompleted) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (useronhold) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (userdropped) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (userplantowatch) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,

  PRIMARY KEY (id)
);

CREATE TABLE AnimeUser (
  userid BIGINT NOT NULL,
  anime BIGINT NOT NULL,
  watchedepisodes INT NOT NULL,
  score REAL NOT NULL,
  FOREIGN KEY (userid) REFERENCES Viewer(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (anime) REFERENCES Anime(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (userid,anime)
);




create temporary table t (
    id VARCHAR(20) NOT NULL PRIMARY KEY,
    title VARCHAR(200),
    title_english VARCHAR(200),
    title_japanese VARCHAR(200),
    title_synonyms VARCHAR(1000),
    imageurl VARCHAR(100),
    format VARCHAR(200),
    source VARCHAR(20),
    episodes VARCHAR(50),
    status VARCHAR(100),
    airing VARCHAR(100),
    aired_string VARCHAR(100),
    aired VARCHAR(500),
    duration VARCHAR(50),
    rating VARCHAR(60),
    score VARCHAR(100),
    scoredby VARCHAR(100),
    rank varchar(100),
    popularity VARCHAR(100),
    members VARCHAR(100),
    favorites VARCHAR(100),
    background VARCHAR(5000),
    premiered VARCHAR(100),
    broadcast VARCHAR(100),
    related VARCHAR(50000),
    producer VARCHAR(1000),
    licensor VARCHAR(100),
    studio VARCHAR(100),
    genre VARCHAR(500),
    opening_theme VARCHAR(5000),
    ending_theme VARCHAR(5000),
    duration_min VARCHAR(100),
    aired_from_year VARCHAR(100)
);

COPY t (
  id,               
  title,
  title_english,
  title_japanese, 
  title_synonyms,
  imageurl,
  format,
  source,
  episodes,
  status,
  airing,
  aired_string,
  aired,
  duration,
  rating,
  score,
  scoredby,
  rank,
  popularity,
  members,
  favorites ,
  background ,
  premiered ,
  broadcast,
  related ,
  producer,
  licensor,
  studio,
  genre,
  opening_theme,
  ending_theme ,
  duration_min,
  aired_from_year
)
FROM '/docker-entrypoint-initdb.d/anime_cleaned.csv'
CSV HEADER;

INSERT INTO Anime (id,title,imageurl,format,source,episodes,status,airing,aired,duration,rating,score,scoredby,rank,popularity,members,genre)
SELECT nullif(id, '')::int as id,title,imageurl,nullif(format, '')::animetype,source,nullif(episodes, '')::INT,nullif(status, '')::animestatus,nullif(AIRING, '')::BOOLEAN,aired,duration,rating,nullif(score, '')::REAL,nullif(scoredby, '')::INT,nullif(rank,'')::REAL,nullif(popularity, '')::int,nullif(members, '')::bigint,genre
FROM t;

DROP TABLE t;


CREATE temporary TABLE t2 (
  username VARCHAR(100),
  id VARCHAR(100),
  userwatching VARCHAR(100),
  usercompleted VARCHAR(100),
  useronhold VARCHAR(100),
  userdropped VARCHAR(100),
  userplantowatch VARCHAR(100),
  userdaysspentwatching VARCHAR(100),
  gender VARCHAR(100),
  location VARCHAR(100),
  birthdate VARCHAR(100),
  accessrank VARCHAR(100),
  joindate VARCHAR(100),
  lastonline VARCHAR(100),
  statsmeanscore VARCHAR(100),
  stats_rewatched VARCHAR(100),
  statsepisodes VARCHAR(100)
);

COPY t2 (
  username,
  id,
  userwatching,
  usercompleted,
  useronhold,
  userdropped,
  userplantowatch,
  userdaysspentwatching,
  gender,
  location,
  birthdate,
  accessrank,
  joindate,
  lastonline,
  statsmeanscore,
  stats_rewatched,
  statsepisodes
)

FROM '/docker-entrypoint-initdb.d/users_cleaned.csv'
CSV HEADER;

INSERT INTO Viewer (username,
  id,
  userwatching,
  usercompleted,
  useronhold,
  userdropped,
  userplantowatch,
  userdaysspentwatching,
  gender,
  location,
  statsepisodes
  )
SELECT username,nullif(t2.id, '')::BIGINT as id,nullif(userwatching, '')::int as userwatching,nullif(usercompleted, '')::int as usercompleted,nullif(useronhold, '')::int as useronhold,nullif(userdropped, '')::int as userdropped,nullif(userplantowatch, '')::int as userplantowatch,nullif(userdaysspentwatching, '')::REAL as userdaysspentwatching,nullif(gender, '')::gender as gender,location,nullif(statsepisodes, '')::int as statsepisodes
FROM t2
WHERE EXISTS(SELECT * FROM Anime where nullif(t2.userwatching,'')::int = Anime.id) AND
      EXISTS(SELECT * FROM Anime where nullif(t2.usercompleted,'')::int = Anime.id) AND
      EXISTS(SELECT * FROM Anime where nullif(t2.useronhold,'')::int = Anime.id) AND
      EXISTS(SELECT * FROM Anime where nullif(t2.userdropped,'')::int = Anime.id) AND
      EXISTS(SELECT * FROM Anime where nullif(t2.userplantowatch,'')::int = Anime.id) AND
      nullif(t2.id, '')::BIGINT not in (select id from Viewer)
      /*AND NOT EXISTS(SELECT 1 from t2, Viewer where nullif(t2.id, '')::int = Viewer.id)*/
;

INSERT INTO AnimeUser(userid,anime,watchedepisodes,score)
VALUES (2255153,1937,586,9);

INSERT INTO AnimeUser(userid,anime,watchedepisodes,score)
VALUES (2255153,2363,26,7);

INSERT INTO AnimeUser(userid,anime,watchedepisodes,score)
VALUES (37326,34870,418,10);

INSERT INTO AnimeUser(userid,anime,watchedepisodes,score)
VALUES (37326,2782,1,0);
