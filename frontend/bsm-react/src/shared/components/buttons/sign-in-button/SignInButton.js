import React from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// utils
import joinClassNames from 'shared/utils/joinClassNames';

// Styles.
import styles from './SignInButton.css';

const getClassNames = (disabled, modifier, isTouchDevice, icon, className) =>
    joinClassNames(
        styles.button,
        !isTouchDevice && styles.hasHover,
        disabled && styles.disabled,
        modifier && styles[modifier],
        className
    );

const SignInButton = ({ disabled, modifier, isTouchDevice, icon, className, isLoading, loaderType, children, ...props }) => (
    <button className={ getClassNames(disabled, modifier, isTouchDevice, className) } disabled={ disabled } { ...props }>
        { isLoading ?
            <FontAwesomeIcon icon={ 'spinner' } size={'lg'} className={ styles.loader } spin /> :
            children
        }
        { !isLoading && <div className={ styles.mask }/> }
        { !isLoading && <FontAwesomeIcon icon={ 'angle-double-right' } size={'2x'} className={ styles.icon } /> }
    </button>
);

SignInButton.propTypes = {
    modifier: PropTypes.string,
    disabled: PropTypes.bool,
    isTouchDevice: PropTypes.bool,
    className: PropTypes.string,
    children: PropTypes.node,
    isLoading: PropTypes.bool,
};

SignInButton.defaultProps = {
    disabled: false,
};

export default SignInButton;
