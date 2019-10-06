import React, {Component} from 'react';
import Client from "./Client";
import AnimeList from "./AnimeList.jsx";
import RecommendationList from './RecommendationList';
import './RecommendationEngine.css'

class RecommendationEngine extends Component {

     constructor(props) {
        super(props);
        this.state = {
            username: '',
            animeRecommendations: []
        };

        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handleSubmitForm = this.handleSubmitForm.bind(this);
  }

    handleUsernameChange(event) {
        this.setState({username: event.target.value});
    }

    handleSubmitForm(event) {
        event.preventDefault();
        var self = this;
        Client.getRecommendation(this.state.username).then(
            function(data){
                console.log(data);
                var recommendations = data.sort((a, b) => parseFloat(b.score) - parseFloat(a.score));
                self.setState({animeRecommendations : recommendations});
            }
        );
    }

    render() {
        var self = this;
        return(
            <div className="recommendation-engine">
                <form id="user-form" onSubmit={this.handleSubmitForm}>
                    <label>
                    <h1 className="input-title">Input the name of the user you would like to make a recommendation for:</h1>
                    <div className="username-form">
                        <input className="username-form-input" type="text" value={this.state.value} onChange={this.handleUsernameChange} name="username" placeholder="username"/>
                        <input className="username-form-submit" type="submit" value="Submit"/>
                    </div>
                    </label>
                    
                </form> 
                {this.state.animeRecommendations.length > 0 ? <RecommendationList animes={this.state.animeRecommendations}/> : ""}
            </div>
        )
    }
}

export default RecommendationEngine;