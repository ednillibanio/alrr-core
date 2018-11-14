$(document)
		.ready(
				function() {
					var xOffset = 10;
					var yOffset = 20;

					$("input")
							.focus(
									function(e) {
										this.t = this.title;
										this.title = "";
										this.width = $(this).width();
										this.position = $(this).position();
										if (this.t!=""){
										$("body")
												.append(
														"<span id='hint'>"
																+ this.t
																+ "<span id='hint-pointer'>&nbsp;</span>"
																+ "</span>");
										$("#hint").css(
												"top",
												(this.position.top)
														+ "px").css(
												"left",
												(this.position.left + this.width + yOffset)
														+ "px").fadeIn("fast");
										}
					});

					$("input").blur(
							function(e) {
								this.title = this.t;
								this.position = $(this).position();
								$("#hint").remove();

								$("#hint").css("top",
										(this.position.top - xOffset) + "px")
										.css(
												"left",
												(this.position.left + yOffset)
														+ "px");
					});
});
