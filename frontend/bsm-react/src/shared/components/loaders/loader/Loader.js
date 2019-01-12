import React from 'react';
// import PropTypes from 'prop-types';
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './Loader.css';

const Loader = ({ className, bounceClassName }) => (
    <div className={ joinClassNames(styles.wrapper, className) }>
        <div className={ joinClassNames(styles.bounce1, bounceClassName) } />
        <div className={ joinClassNames(styles.bounce2, bounceClassName) } />
        <div className={ joinClassNames(styles.bounce3, bounceClassName) } />
    </div>
);

// Loader.propTypes = {
//     className: PropTypes.string,
//     bounceClassName: PropTypes.string,
//     dataTest: PropTypes.string,
// };

export default Loader;
