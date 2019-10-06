import React, {Component} from 'react';
import AnimeCard from './AnimeCard.jsx';
import './AnimeList.css'
import Client from './Client.js'

class AnimeList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            animes: []
        }
    }

    async componentDidMount() {
        
        this.props.typeOfList === "top" ?
            Client.getTopRated(animes => {
                console.log(animes)
                this.setState({
                    animes: animes
                });
                })
        :
            Client.getAllAnimes(animes => {
                this.setState({
                    animes: animes
                })
            })
        ;
    }


    render() {
        var animesList = this.state.animes.length > 0 ? this.state.animes.map(function(anime){
        return (
            <AnimeCard
              key={anime.id}
              id={anime.id}
              title={anime.title}
              imageUrl={anime.imageUrl}
              score={anime.score}
              scoredBy={anime.scoredBy}
            />)
            }
        ) : "";
        return (
            <div className="animes">
                {this.props.typeOfList === "top" ? <h1>Top 10 Animes</h1> : <h1>List of available Animes</h1>}
                <table className="anime-list" border="1">
                <colgroup>
                    <col className="columnPoster"/>
                    <col className="columnTitle"/>
                    <col className="columnScore"/>
                    <col className="columnScoredBy"/>
                </colgroup>
                    <thead>
                        <tr>
                            <th></th>
                            <th>Title</th>
                            <th>Score</th>
                            <th>Ranked By</th>
                        </tr>
                    </thead>
                    <tbody>
                    {animesList}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default AnimeList;
