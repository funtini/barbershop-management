import React from 'react';
import PropTypes from 'prop-types';
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './Loader.css';

const Loader = ({ className, bounceClassName, type, inverseColor }) => {
    switch(type) {
        case 'dots':
            return dotsLoader({ className,bounceClassName, inverseColor });
        case 'dual-ring':
            return dualRingLoader({ className, inverseColor });
        case 'bars':
            return barsLoader({ className, inverseColor });
        default:
            return dotsLoader({ className,bounceClassName, inverseColor });
    }
};


const dotsLoader = ({ className, bounceClassName, inverseColor }) => (
    <div className={ joinClassNames(styles.wrapper, className) }>
        <div className={ joinClassNames(styles.bounce1, bounceClassName, inverseColor && styles.inverseColor) } />
        <div className={ joinClassNames(styles.bounce2, bounceClassName, inverseColor && styles.inverseColor) } />
        <div className={ joinClassNames(styles.bounce3, bounceClassName, inverseColor && styles.inverseColor) } />
    </div>
);

const dualRingLoader = ({ className, inverseColor }) => (
    <div className={ joinClassNames(styles["dual-ring"], className, inverseColor && styles.inverseColor) }/>
);

const barsLoader = ({ className, inverseColor }) => (
    <div className={ joinClassNames(styles.bars, className, inverseColor && styles.inverseColor) }>
        <div/>
        <div/>
        <div/>
    </div>
);

Loader.propTypes = {
    className: PropTypes.string,
    bounceClassName: PropTypes.string,
};

export default Loader;
