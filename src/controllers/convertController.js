let currencies = require('../services/loadDataService');
let paramsFilter = require('./filters/paramsFilter');
require('../services/cacheService').instance();
let numbersUtil = require("../util/numbers");

currenciesConvert = new currencies();

exports.get = async (req, res, next) => {
    try {
        let from = req.query.from.toUpperCase(), to = req.query.to.toUpperCase(), amount = req.query.amount;
        if (paramsFilter.paramsFilters(req.query)) {
            const valueconvert = await currenciesConvert.getConversionCurrencies(from, to, amount);
            const response = await setResponse(
                from,
                amount,
                to,
                valueconvert
            );
            res.status(200).json(response);
        } else {
            res.status(400).json({error: 'Bad Request'});
        }
    } catch (err) {
        next(err);
    }
};

exports.getfriendly = ("/:from/:to/:amount", async function (req, res, next) {
    try {
        let from = req.params.from.toUpperCase(), to = req.params.to.toUpperCase(), amount = req.params.amount;
        if (paramsFilter.paramsFilters(req.params)) {
            const valueconvert = await currenciesConvert.getConversionCurrencies(from, to, amount);
            const response = await setResponse(
                from,
                amount,
                to,
                await valueconvert
            );
            res.status(200).json(response);
        } else {
            res.status(400).json({error: 'Bad Request'});
        }
    } catch (err) {
        next(err);
    }
});


const setResponse = async function setResponse(origin, amount, destiny, amountResult) {
    return {
        original: {
            currency: origin,
            amount: numbersUtil.formatCurrency(amount)
        },
        result: {
            currency: destiny,
            amount: numbersUtil.formatCurrency(amountResult)
        }
    };
};