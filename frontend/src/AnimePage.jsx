import React, {Component} from 'react';
import Client from "./Client";
import './AnimePage.css'


class AnimePage extends Component {

    constructor(props){
        super(props);
        this.state = {
            animeData : null
        }
    }

    componentDidMount() {
        const { params } = this.props.match
        Client.getAnime(params.id).then(anime => {
            this.setState({
                animeData: anime
            })
        })
    }

    renderAnimeInfo() {
        var self = this;
        var animeInfo = Object.keys(this.state.animeData).map(function(key) {
            if(key !== "title" && key !== "imageUrl" && key !== "id" ){
                return(
                    <li key={key}>{key} : {self.state.animeData[key]}</li>
                )
            }
          })          
        return animeInfo;
    }

    renderAnimePage() {
        var anime = this.state.animeData;
        return(
            <div>
                <h1> <p>{anime.title}</p> <img src={anime.imageUrl} /></h1>
                <ul className="anime-info-list">
                    {this.renderAnimeInfo()}
                </ul>
            </div>
        )
    }

    render(){
        var self = this;
        return(
            <div>
                {self.state.animeData != null ? self.renderAnimePage() : ""}
            </div>
        );
    }

}

export default AnimePage;