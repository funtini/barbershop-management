import React from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// utils
import joinClassNames from 'shared/utils/joinClassNames';

// Components
import Button from 'shared/components/buttons/button/Button';
import Loader from 'shared/components/loaders/loader/Loader';

// Styles.
import styles from './LoaderButton.css';


const getClassNames = (isLoading, className) =>
    joinClassNames(
        styles.wrapper,
        isLoading && styles.buttonClickDisabled,
        className,
    );

const LoaderButton = ({ role, disabled, isTouchDevice, className, children, filled, loaderType, isLoading, ...props }) => (
    <Button
        className={ getClassNames(isLoading, className) }
        role={ role }
        disabled={ disabled }
        isTouchDevice={ isTouchDevice }
        { ...props }>
        { isLoading && ( loaderType ?
            <Loader className={ styles.loader } type={ loaderType } inverseColor={ filled } /> :
            <FontAwesomeIcon icon={ 'spinner' } size={'lg'} className={ styles.spinner } spin />
        )}
        { !isLoading && children }
    </Button>
);

LoaderButton.propTypes = {
    ...Button.propTypes,
    isLoading: PropTypes.bool,
};

LoaderButton.defaultProps = Button.defaultProps;

export default LoaderButton;
