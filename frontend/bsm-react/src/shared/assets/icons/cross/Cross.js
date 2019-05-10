import React from 'react';
import PropTypes from 'prop-types';

import joinClassNames from 'shared/utils/joinClassNames';

// Styles.
import styles from './Cross.css';

const Cross = ({ className, ...props }) => (
    <div className={ joinClassNames(styles.wrapper, className) } { ...props }/>
);

Cross.propTypes = {
    className: PropTypes.string,
    onClick: PropTypes.func,
};

export default Cross;
