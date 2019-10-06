import React, {Component} from 'react';

import starLogo from './images/star.png'

class RecommendationCard extends Component {

    renderScore(score) {
        return Math.round(score * 100)/100;
    }

    render() {
        return(
            <tr className="anime-table-row">
                <td><span><img style={{height: '20vh'}} src={this.props.imageUrl} /></span></td>
                <td><p>{this.props.title}</p></td>
                <td> <p><img style={{height: '20px'}} src={starLogo} />{this.renderScore(this.props.score)}</p></td>
                <td><p>{this.props.scoredBy}</p></td>
            </tr>
        )
    }
}

export default AnimeCard;
