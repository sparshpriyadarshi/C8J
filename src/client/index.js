"use strict";

console.log("index js loads");

const DISPLAY_W = 64
const DISPLAY_H = 32
const DISPLAY_SCALE = 10
const OFF_RGB = { R:0,G:0,B:0}
const ON_RGB = { R:200,G:220,B:255}
const EMU_STATUSES = {
	STARTED: "STARTED",
	STOPPED: "STOPPED"
};
function Initialize() {
	const canvas = document.getElementById("main-canvas");
	canvas.width = DISPLAY_W * DISPLAY_SCALE;
	canvas.height = DISPLAY_H * DISPLAY_SCALE;
    const ctx = canvas.getContext("2d");

	//BG
	ctx.fillStyle = `rgb(
        ${OFF_RGB.R},
        ${OFF_RGB.G},
        ${OFF_RGB.B}
        )`;
    ctx.fillRect(0, 0, canvas.width, canvas.height);

	//FG
	ctx.fillStyle = `rgb(
        ${ON_RGB.R},
        ${ON_RGB.G},
        ${ON_RGB.B}
        )`;
    ctx.fillRect(12, 12, DISPLAY_SCALE, DISPLAY_SCALE);
	
	const status = document.getElementById("player-status-text");
	status.innerText = `STATUS: ${EMU_STATUSES.STOPPED}`;

	const startButton = document.getElementById("start-button");
	startButton.addEventListener("click", Start);
	const stopButton = document.getElementById("stop-button");
	stopButton.addEventListener("click", Stop);
}

function Start(){
	console.log("Emulator started...");
		
	const status = document.getElementById("player-status-text");
	status.innerText = `STATUS: ${EMU_STATUSES.STARTED}`;
}

function Stop(){
	console.log("Emulator stopped...");
		
	const status = document.getElementById("player-status-text");
	status.innerText = `STATUS: ${EMU_STATUSES.STOPPED}`;
}
Initialize();

/*
 (function () {
  "use strict";
  function greetMe(yourName) {
    alert(`Hello ${yourName}`);
  }

  greetMe("World");
})();
*/
