import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';

// Style
import styles from './Welcome.css';

class Welcome extends Component {
    constructor(props) {
        super(props);

        this._handleClick = this._handleClick.bind(this);
    }

    render() {
        return (
            <div className={ styles.wrapper }>
                <header>
                    <nav className={ styles.navigation }>
                        NavBar
                    </nav>
                    <div className={ styles.hero }>
                        Hero
                    </div>
                </header>
                <main>
                    Welcome Page
                    <section className={ styles.welcome }>
                        Welcome Section
                    </section>
                    <section className={ styles.whatWeDo }>
                        What we do
                    </section>
                    <section className={ styles.pricing }>
                        Pricing Section
                    </section>
                    <section className={ styles.hairStyle }>
                        HairStyle Gallery
                    </section>
                    <section className={ styles.productShop }>
                        Product Shop
                    </section>
                    <section className={ styles.booking }>
                        Booking Gallery
                    </section>
                </main>
            </div>
        );
    }

    _handleClick(){
        this.props.history.push('/')
    }
}

Welcome.propTypes = {

};

export default withRouter(Welcome);
