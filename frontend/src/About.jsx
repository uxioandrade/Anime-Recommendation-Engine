import React, {Component} from 'react';
import { SocialIcon } from 'react-social-icons';
import './About.css'

class About extends Component {

    render() {
        return(
            <div>
                <h1>About</h1>
                <p className="about-text">This project has been developed by Uxío García Andrade.
                The purpose of this project is to experiment with different types of recommendation algorithms, as well
                as to build a fullstack app around them</p>
                <div className="social-icons">
                    <SocialIcon url="http://twitter.com/Uxio21" />
                    <SocialIcon url="https://www.linkedin.com/in/uxio21/" />
                    <SocialIcon url="https://github.com/UxioAndrade" />
                </div>
            </div>
        )
    }
}

export default About;