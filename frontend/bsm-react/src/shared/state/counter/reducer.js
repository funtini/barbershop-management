import * as actionTypes from './actionTypes';

const initialState = {
    result: 0,
};

const incrementCount = (state, action) => {
    return state.result+1;
};

const decrementCount = (state, action) => {
    return state.result-1;
};

const counterReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.INCREMENT_COUNT:
            return incrementCount(state,action);
        case actionTypes.DECREMENT_COUNT:
            return decrementCount(state,action);
        default:
            return state
    }
};

export default counterReducer;
