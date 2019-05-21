import React from 'react';
import PropTypes from 'prop-types';

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

const LoaderButton = ({ role, disabled, isTouchDevice, className, children, filled, type, isLoading, ...props }) => (
    <Button
        className={ getClassNames(isLoading, className) }
        role={ role }
        disabled={ disabled }
        isTouchDevice={ isTouchDevice }
        { ...props }>
        { isLoading && (
            <Loader className={ styles.loader } type={ type } inverseColor={ filled } />
        ) }
        { !isLoading && children }
    </Button>
);

LoaderButton.propTypes = {
    ...Button.propTypes,
    isLoading: PropTypes.bool,
};

LoaderButton.defaultProps = Button.defaultProps;

export default LoaderButton;
