import React from 'react';
import PropTypes from 'prop-types';
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './Loader.css';

const Loader = ({ className, bounceClassName, type }) => {
    switch(type) {
        case 'dots':
            return dotsLoader({ className,bounceClassName });
        case 'dual-ring':
            return dualRingLoader({ className });
        case 'bars':
            return barsLoader({ className });
        default:
            return dotsLoader({ className,bounceClassName });
    }
};


const dotsLoader = ({ className, bounceClassName }) => (
    <div className={ joinClassNames(styles.wrapper, className) }>
        <div className={ joinClassNames(styles.bounce1, bounceClassName) } />
        <div className={ joinClassNames(styles.bounce2, bounceClassName) } />
        <div className={ joinClassNames(styles.bounce3, bounceClassName) } />
    </div>
);

const dualRingLoader = ({ className }) => (
    <div className={ joinClassNames(styles["dual-ring"], className) }/>
);

const barsLoader = ({ className }) => (
    <div className={ joinClassNames(styles.bars, className) }>
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
