import React, { Component } from 'react';
import PropTypes from 'prop-types';

// Components
import FadeTransition from 'shared/components/animations/fade-transition/FadeTransition';
import NotificationBox from './notification-box/NotificationBox';

class Notification extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: true,
        };
        //state data
        this._handleClose = this._handleClose.bind(this);
        this._handleChangeOpenStatus = this._handleChangeOpenStatus.bind(this);
    }

    componentWillMount() {
        const { shouldOpen } = this.props;

        if (shouldOpen !== undefined && !shouldOpen) {
            this.setState({
                isOpen: false,
            })
        }
    }

    componentDidUpdate() {
        const { shouldOpen } = this.props;
        const { isOpen } = this.state;

        if (shouldOpen && !isOpen){
            this._handleChangeOpenStatus();
        }
    }


    render() {
        const { children, onClose, ...remainingProps } = this.props;

        return(
            <FadeTransition show={ this.state.isOpen } timeout={ 300 }>
                <NotificationBox close={ this._handleClose } { ...remainingProps } >
                    { children }
                </NotificationBox>
            </FadeTransition>
        )
    }

    _handleChangeOpenStatus(){
        this.setState((prevState) => ({
            isOpen: !prevState.isOpen,
        }));
    }

    _handleClose(){
        const { onClose } = this.props;

        this._handleChangeOpenStatus();

        onClose && onClose();
    }
}

Notification.propTypes = {
    children: PropTypes.node,
    onClose: PropTypes.func,
    shouldOpen: PropTypes.bool,
};

export default Notification;
