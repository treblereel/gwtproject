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

if (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {
	displayTimeZone = function(daylight, timeZone, style, locale) {
		const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });
		const month = daylight ? 6 : 12;
		const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
		return region.formatToParts(date).find(checkType).value;
	};
} else {
	displayTimeZone = function(daylight, timeZone, style, locale) {
		return timeZone;
	};
}

function sleep(milliseconds) {
	var start = getTimestamp();
	var current = getTimestamp();
	while(current - start < milliseconds) {
		current = getTimestamp();
	}
}

function displayTimeZoneModern(daylight, timeZone, style, locale) {
	const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });
	const month = daylight ? 6 : 12;
	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
	return region.formatToParts(date).find(checkType).value;
}

function checkType(part) {
	  return part.type === "timeZoneName";
}

function displayTimeZoneLegacy(daylight, timeZone, style, locale) {
	const month = daylight ? 6 : 12;
	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));
	return date.toLocaleDateString(locale, { timeZone: timeZone, timeZoneName: style }).slice(11);
}