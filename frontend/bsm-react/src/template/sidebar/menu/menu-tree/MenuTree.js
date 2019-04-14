import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './MenuTree.css';

class MenuTree extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isOpen: false
        };
    }


    componentWillReceiveProps(nextProps) {
        if (!nextProps.selected){
            this.setState({
                isOpen: false
            })
        }
    }


    render() {
        const { path, icon, label, children, isCollapsed } = this.props;
        const { isOpen } = this.state;
        const shouldTreeExpand = isOpen && !isCollapsed;
        const labelClass = joinClassNames(
            styles.label,
            isCollapsed && styles['label-collapsed'],
        );
        const arrowClass = joinClassNames(
            isCollapsed && styles['arrow-collapsed'],
            shouldTreeExpand && styles.open,
            !shouldTreeExpand && styles.close
        );
        const subMenuClass = joinClassNames(
            styles.subMenu,
            shouldTreeExpand && styles.expand,
            !shouldTreeExpand && styles.collapse
        );

        const menuClass = joinClassNames(
            shouldTreeExpand && styles.active,
            isCollapsed && styles.menu,
        );

        return (
            <li className={ styles.treeView } >
                <a href={ path }  className={ menuClass } onClick={ this._handleClick } >
                    <FontAwesomeIcon icon={ icon } className={ styles.icon }/>
                    <span className={ labelClass }>{ label }</span>
                    <span className={ styles.arrowWrapper }>
                        <FontAwesomeIcon icon={'angle-left'} className={ arrowClass } />
                    </span>
                </a>
                <ul className={ subMenuClass }>
                    { children }
                </ul>
            </li>
        );
    }

    _handleClick = () => {
        const { isCollapsed, onClick } = this.props;
        const { isOpen } = this.state;

        if (isCollapsed){

        }

        onClick(this.props.label, true);

        this.setState(prevState => ({
            isOpen: !prevState.isOpen
        }))
    };

}

export default MenuTree;
