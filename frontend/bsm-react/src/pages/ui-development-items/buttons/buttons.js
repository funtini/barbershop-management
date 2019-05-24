import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';

import Button from 'shared/components/buttons/button/Button';
import LoaderButton from 'shared/components/buttons/loader-button/LoaderButton';

//styles
import styles from './buttons.css';


class Buttons extends Component {
    constructor(props) {
        super(props);
    }

    render () {
        const { t } = this.props;

        return (
            <div className={ styles.wrapper }>
                <p>BUTTONS</p>
                <p>
                    <Button className={ styles.button } role={'primary'} wide modifier={'outlineButton'} type={'submit'}>Outline Button</Button>
                    <Button className={ styles.button } role={'primary'} wide modifier={'buttonFill'} type={'submit'}>Fill Button</Button>
                    <Button className={ styles.button } role={'primary'} wide gradient modifier={'buttonUnFill'} type={'submit'}>UnFill Button</Button>
                    <Button className={ styles.button } role={'unboxed'} wide type={'submit'}>unboxed</Button>
                    <Button className={ styles.button } role={'primary'} wide type={'submit'}>Primary</Button>
                    <LoaderButton className={ styles.button } filled role={'primary'} wide type={'submit'} isLoading={true}>Loader Button</LoaderButton>
                </p>
            </div>
        )}
}

export default withTranslation()(Buttons);
