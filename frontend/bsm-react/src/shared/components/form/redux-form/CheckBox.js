import React from 'react';
import PropTypes from 'prop-types';

import Checkbox from '../checkbox/Checkbox';

const ReduxFormCheckbox = ({ input, meta, ...rest }) => (
    <Checkbox
        checked={ !!input.value }
        error={ (meta.submitFailed || (meta.touched && meta.dirty)) && meta.error ? meta.error : undefined }
        { ...input }
        { ...rest } />
);

ReduxFormCheckbox.propTypes = {
    meta: PropTypes.object.isRequired,
    input: PropTypes.object.isRequired,
};

export default ReduxFormCheckbox;
