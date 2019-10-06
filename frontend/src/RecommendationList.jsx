import React, {Component} from 'react';
import AnimeCard from './AnimeCard.jsx';
import './AnimeList.css'
import Client from './Client.js'

class RecommendationList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            animes: this.props.animes
        }
    }

    /*async componentDidMount() {
        Client.getTopRated(animes => {
            console.log(animes)
            this.setState({
              animes: animes
            });
          });
    }*/


    render() {
        var animesList = this.state.animes.length > 0 ? this.state.animes.map(function(anime){
        return (
            <AnimeCard
              key={anime.id}
              id={anime.id}
              title={anime.title}
              imageUrl={anime.imageUrl}
              score={anime.score}
            />)
            }
        ) : "";
        return (
            <table className="anime-list">
            <colgroup>
                <col className="columnPoster"/>
                <col className="columnTitle"/>
                <col className="columnScore"/>
            </colgroup>
                <thead>
                    <tr>
                        <th></th>
                        <th>Title</th>
                        <th>Score</th>
                    </tr>
                </thead>
                <tbody>
                {animesList}
                </tbody>
            </table>
        )
    }
}

export default RecommendationList;
