import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { withTranslation } from 'react-i18next';

import userImage from 'shared/assets/images/user.jpg';

// Styles
import styles from './LockScreen.css';


class LockScreen extends Component {
    render() {
        const { t } = this.props;

        return (
            <div className={ styles.lockScreen }>
            <div className={ styles.wrapper }>
                <div className={ styles.brand }>
                    <b>{ t('brand.name') }</b> { t('brand.suffix') }
                </div>
                <div className={ styles.username }>
                    Pedro Domingos
                </div>
                <div className={ styles.lockItem }>
                    <div className={ styles.imageWrapper }>
                        <img className={ styles.image } src={ userImage } alt={'user'} />
                    </div>
                    <form className={ styles.credentials }>
                        <div className={ styles.formGroup }>
                            <input type={'password'} className={ styles.formControl } placeholder={'password'}/>
                            <div className={ styles.formButtonWrapper }>
                                <button className={ styles.button }>
                                    <FontAwesomeIcon icon={ 'sign-in-alt'} size={'lg'} className={ styles.icon } color={ 'grey'}/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div className={ styles.helperText }>
                    Enter your password to retrieve your session
                </div>
                <div className={ styles.link }>
                    <a>Or sign in as a different user</a>
                </div>
                <div className={ styles.footer }>
                    Copyright © 2019 <strong>João Gomes</strong>
                    <br/>All rights reserved
                </div>
            </div>
            </div>
        );
    }
}

LockScreen.propTypes = {

};

export default withTranslation(['header'])(LockScreen);
