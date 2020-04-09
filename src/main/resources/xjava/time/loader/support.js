if (window.performance.now) {
    getTimestamp = function() { return window.performance.timing.navigationStart + window.performance.now(); };
} else {
    getTimestamp = function() { return new Date().getTime(); };
}

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().resolvedOptions == 'function') {
	getTimezone = function() { return Intl.DateTimeFormat().resolvedOptions().timeZone; };
} else {
	getTimezone = function() { return new Date().toTimeString().slice(9, 17); };
}

