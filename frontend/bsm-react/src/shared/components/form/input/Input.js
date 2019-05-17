import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';

// components
import LinkButton from 'shared/components/buttons/link-button/LinkButton';

// utils
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './Input.css';

class Input extends PureComponent {
    constructor(props) {
        super(props);

        this._handleOnChange = this._handleOnChange.bind(this);
        this._handleKeyPress = this._handleKeyPress.bind(this);
    }

    render() {
        const {
            id,
            className,
            buttonLink,
            disabled,
            error,
            label,
            type,
            wide,
            setInputRef,
            onChange, // eslint-disable-line no-unused-vars
            name,
            ...rest
        } = this.props;

        const value = this.props.value;
        const inputName = name || ((label || '').replace(/\W/g, '-').toLowerCase());
        const inputId = id || `input-${inputName}`;
        const pattern = type === 'number' ? '[0-9]*' : undefined;

        const wrapperClassName = joinClassNames(styles.wrapper,
            disabled && styles.disabled,
            error && styles.error,
            wide && styles.wide,
            className);
        const inputWrapperClassName = joinClassNames(styles.inputWrapper,
            disabled && styles.disabled,
            error && styles.inputWrapperError);

        const bottomLineClassName = joinClassNames(styles.bottomLine, {
            [styles.hasValue]: value && value !== '',
        });
        const labelClassName = joinClassNames(styles.label, {
            [styles.hasValue]: value && value !== '',
        });
        const inputClassName = joinClassNames(styles.input,
            error && styles.inputError,
            buttonLink && styles.inputWithButton
        );

        return (
            <div className={ wrapperClassName }>
                <div className={ inputWrapperClassName }>
                    <input
                        id={ inputId }
                        name={ inputName }
                        type={ type }
                        onKeyPress={ this._handleKeyPress }
                        onChange={ this._handleOnChange }
                        disabled={ disabled }
                        className={ inputClassName }
                        aria-label={ label }
                        ref={ setInputRef }
                        pattern={ pattern }
                        { ...rest } />

                    {/*{ label && <label className={ labelClassName } htmlFor={ inputId }>{ label }</label> }*/}
                    {/*<div className={ bottomLineClassName } />*/}
                    { buttonLink && <LinkButton type="submit" className={ styles.buttonLink }>{ buttonLink }</LinkButton> }
                </div>
                { this.renderError() }
            </div>
        );
    }

    renderError() {
        const { error } = this.props;

        if (typeof error !== 'string' || !error) {
            return;
        }

        return (
            <div className={ styles.error }>
                { error }
            </div>
        );
    }

    _handleKeyPress(e) {
        const maxLength = parseInt(this.props.maxLength, 10);

        if (!isNaN(maxLength) && e.target.value.length >= maxLength) {
            e.preventDefault();
        }
    }

    _handleOnChange(e) {
        const { onChange } = this.props;

        onChange && onChange(e.target.value);
    }
}

Input.defaultProps = {
    type: 'text',
};

Input.propTypes = {
    id: PropTypes.string,
    name: PropTypes.string,
    className: PropTypes.string,
    buttonLink: PropTypes.string,
    disabled: PropTypes.bool,
    error: PropTypes.oneOfType([PropTypes.string, PropTypes.bool]),
    label: PropTypes.string,
    value: PropTypes.string,
    onChange: PropTypes.func,
    type: PropTypes.oneOf(['text', 'number', 'password', 'email']),
    wide: PropTypes.bool,
    setInputRef: PropTypes.func,
    mask: PropTypes.object,
    maxLength: PropTypes.string,
};

export default Input;
