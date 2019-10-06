import React, {Component} from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link,
  Switch,
  Redirect
} from 'react-router-dom';
import starLogo from './images/star.png'

import './AnimeCard.css'

class AnimeCard extends Component {

    renderScore(score) {
        return Math.round(score * 100)/100;
    }

    render() {
        return(
            
                <tr className="anime-table-row">
                    <td style={{height: '20vh'}}><span ><img className="table-image" src={this.props.imageUrl} /></span></td>
                    <td>
                        <Link to={"/anime/" + this.props.id}>
                            <p>{this.props.title}</p>
                        </Link>
                    </td>
                    <td> <p><img style={{height: '20px', textAlign: 'center'}} src={starLogo} />{this.renderScore(this.props.score)}</p></td>
                    {this.props.scoredBy != undefined ? <td><p style={{textAlign: 'center'}}>{this.props.scoredBy}</p></td> : ""}
                </tr>
        )
    }
}

export default AnimeCard;
