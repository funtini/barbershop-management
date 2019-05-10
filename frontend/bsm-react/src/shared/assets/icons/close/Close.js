import React from 'react';
import PropTypes from 'prop-types';

import joinClassNames from 'shared/utils/joinClassNames';

// Components.
import Cross from '../cross/Cross';

// Styles.
import styles from './Close.css';

const Close = ({ className }) => (
    <div className={ joinClassNames(styles.wrapper, className) }>
        <Cross className={ styles.cross }/>
    </div>
);

Close.propTypes = {
    className: PropTypes.string,
};

export default Close;
