import React, { Component } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import LinkButton from '../../../shared/components/buttons/link-button/linkButton'

//styles
import styles from './headerNavigation.css';

class HeaderNavigation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            svgIcons: [],
        }
        //state data
    }

    render(){
        return(
            <ul className={styles.navBar}>
                { this._renderMessages() }
                { this._renderNotifications() }
            </ul>
        )
    }

    _renderMessages(){
        return(
            <li className={ styles.option }>
                <LinkButton className={ styles.link } onClick={ () => (console.log("CLICK"))}>
                    <FontAwesomeIcon icon={'envelope'} inverse/>
                    {/*BADGE*/}
                </LinkButton>
            </li>
        )
    }

    _renderNotifications(){
        return(
            <li className={ styles.option }>
                <LinkButton className={ styles.link } onClick={ () => (console.log("CLICK"))}>
                    <FontAwesomeIcon icon={'flag'} />
                    {/*BADGE*/}
                </LinkButton>
            </li>
        )
    }
}

export default HeaderNavigation;