import React from 'react';
import joinClassNames from 'shared/utils/joinClassNames';
import PropTypes from 'prop-types';

// style
import styles from './Checkbox.css';

const createChangeHandler = (id, onChangeCallback) => (event) => {
    if (event.key) {
        if (event.key !== ' ' && event.key !== 'Enter') {
            return;
        }
        event.key === ' ' && event.preventDefault();
        event.target.checked = !event.target.checked;
    }

    onChangeCallback && onChangeCallback(event.target.checked, id);
};

const Checkbox = ({ id, name, className, ellipsis, label, labelPosition, onChange, checked, error, disabled, dataTest }) => {
    const checkboxClassName = joinClassNames(styles.checkbox, {
        [styles[labelPosition]]: labelPosition !== 'after',
        [styles.hasError]: !!error,
        [styles.disabled]: !!disabled,
    }, className);

    const handleOnChange = createChangeHandler(id, onChange);

    return (
        <label tabIndex="0" onKeyDown={ handleOnChange } htmlFor={ id } className={ checkboxClassName }>
            <input
                type="checkbox"
                id={ id }
                name={ name }
                data-test={ dataTest }
                className={ styles.input }
                onChange={ handleOnChange }
                checked={ checked }
                disabled={ disabled } />

            <span className={ styles.square } />

            { label && <span className={ joinClassNames(styles.label, ellipsis && styles.ellipsis) }>{ label }</span> }
            { error && <span className={ joinClassNames(styles.label, styles.error) }>{ error }</span> }
        </label>
    );
};

Checkbox.defaultProps = {
    labelPosition: 'after',
};

Checkbox.propTypes = {
    id: PropTypes.string,
    name: PropTypes.string,
    className: PropTypes.string,
    ellipsis: PropTypes.bool,
    label: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
    labelPosition: PropTypes.oneOf(['before', 'after']),
    onChange: PropTypes.func,
    checked: PropTypes.bool,
    error: PropTypes.string,
    disabled: PropTypes.bool,
    dataTest: PropTypes.string,
};

export default Checkbox;

