/* eslint-disable no-undef */
function getSummary(cb) {
  return fetch('/api/summary', {
    accept: "application/json"
  })
    .then(checkStatus)
    .then(parseJSON)
    .then(cb);
}

function getTopRated(cb) {
  return fetch('/animes/toprated', {
    accept: "application/json"
  })
    .then(checkStatus)
    .then(parseJSON)
    .then(cb)
}

function getAllAnimes(cb) {
  return fetch('/animes/all', {
    accept: "application/json"
  })
    .then(checkStatus)
    .then(parseJSON)
    .then(cb)
}

function getRecommendation(data) {
  return fetch('/recommendations/' + data, {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            username: data
          })
          })
          .then(checkStatus)
          .then(parseJSON)
}

function getAnime(id) {
  console.log("request id " + id);
  return fetch('/anime/' + id, {
    accept: "application/json"
  })
    .then(console.log(id))
    .then(checkStatus)
    .then(parseJSON)
}

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }

  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  console.log(error); // eslint-disable-line no-console
  throw error;
}

function parseJSON(response) {
  return response.json();
}

const Client = { getSummary, getTopRated, getRecommendation, getAllAnimes, getAnime};
export default Client;
