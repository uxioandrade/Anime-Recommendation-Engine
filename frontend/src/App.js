import React, {Component} from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link,
  Switch
} from 'react-router-dom';

import Icon from './images/icon.png'
import naruto from './images/naruto.png'
import Client from "./Client";
import AnimeList from "./AnimeList.jsx"
import './App.css';
import RecommendationEngine from './RecommendationEngine.jsx'
import About from './About.jsx'
import Home from './Home.jsx'
import AnimePage from './AnimePage.jsx'

const Tech = ({match}) => {
  return <div>Current Route: {match.params.tech}</div>
};


class App extends Component {
  constructor(props) {
    super(props);
    this.state = {title: '',
    animes: []
    };
  }

  async componentDidMount() {
  }

  render() {
    return (
      <Router>
        <div className="App" style={{width: '100%'}}>
        <ul>
          <li>
            <Link to="/home">
              <a href="/home" className="logo"></a>
            </Link>
          </li>
          <li>
            <Link href="/animes" to="/animes">
              <a>List of Animes</a>  
            </Link>
          </li>
          <li href="/topRated">
            <Link  to="/topRated">
              <a>Top Rated Animes</a>
            </Link>
          </li>
          <li href="/recommendationEngine">
            <Link  to="/RecommendationEngine">
              <a>Recommend Anime</a>
            </Link>  
          </li>
          <li href="/about">
            <Link  to="/about">
            <a >About</a>
            </Link>
          </li>
        </ul>
        </div>
        <Switch>
          <Route path="/home">
            <Home/>
          </Route>
          <Route path="/animes">
            <AnimeList typeOfList="all"/>
          </Route>
          <Route path="/about">
            <About/>
          </Route>
          <Route path="/topRated">
            <AnimeList typeOfList="top"/>
          </Route>
          <Route path="/RecommendationEngine">
            <RecommendationEngine />
          </Route>
          <Route path="/anime/:id" component={AnimePage} />
          <Route path="/"/>
            <Home/>
          <Route/>
        </Switch>
      </Router>
    );
  }
}

export default App;
