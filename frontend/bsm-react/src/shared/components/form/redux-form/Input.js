import React from 'react';
import PropTypes from 'prop-types';

import Input from '../input/Input';

const ReduxFormInput = ({ input, meta, ...rest }) => (
    <Input
        error={ (meta.submitFailed || (meta.touched && meta.dirty)) && meta.error ? meta.error : undefined }
        { ...input }
        { ...rest } />
);

ReduxFormInput.propTypes = {
    meta: PropTypes.object.isRequired,
    input: PropTypes.object.isRequired,
};

export default ReduxFormInput;
