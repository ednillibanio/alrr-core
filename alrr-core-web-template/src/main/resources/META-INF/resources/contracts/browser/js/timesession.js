/**
 * 
 */
var SessionTime = new (function() {

	var $countdown;
	var $form;
	var incrementTime = 70;
	var currentTime = 300000;
	// 5
	// minutes
	// (in
	// milliseconds)

	$(function() {

		// Setup the timer
		$countdown = $('#countdown');
		SessionTime.Timer = $.timer(updateTimer, incrementTime, true);
		SessionTime.resetCountdown();
		// Setup form

		$form = $('#frmMenu');
		$form.bind('#keepSessionAlive', function() {
			SessionTime.resetCountdown();
			return false;
		});

	});

	function updateTimer() {

		// Output timer position
		var timeString = formatTime(currentTime);
		$countdown.html(timeString);

		// If timer is complete, trigger alert
		if (currentTime == 0) {
			SessionTime.Timer.stop();
			alert('Example 2: Countdown timer complete!');
			SessionTime.resetCountdown();
			return;
		}

		// Increment timer position
		currentTime -= incrementTime;
		if (currentTime < 0)
			currentTime = 0;

	}

	this.resetCountdown = function() {

		// Get time from form
		var newTime = parseInt($form.find('input[type=hidden]').val()) * 1000;
		if (newTime > 0) {
			currentTime = newTime;
		}

		// Stop and reset timer
		SessionTime.Timer.stop().once();

	};

});
