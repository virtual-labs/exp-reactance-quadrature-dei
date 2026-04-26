class SynchronousMachineSimulation {
    constructor() {
        this.connections = { phase1: null, phase2: null, phase3: null };
        this.threePhaseOn = false;
        this.twoPhaseOn = false;

        this.wireOutputs = {
            phase1: document.getElementById('wire1'),
            phase2: document.getElementById('wire2'),
            phase3: document.getElementById('wire3')
        };
        this.statusText = document.getElementById('statusText');
        this.phaseMap = document.getElementById('phaseMap');
        this.rotationArrow = document.getElementById('rotationArrow');
        this.threePhaseState = document.getElementById('threePhaseState');
        this.twoPhaseState = document.getElementById('twoPhaseState');

        this.bindEvents();
        this.updateInterface();
    }

    bindEvents() {
        document.querySelectorAll('input[name="phase1"]').forEach(radio => {
            radio.addEventListener('change', (event) => {
                this.connections.phase1 = event.target.value;
                this.updateInterface();
            });
        });

        document.querySelectorAll('input[name="phase2"]').forEach(radio => {
            radio.addEventListener('change', (event) => {
                this.connections.phase2 = event.target.value;
                this.updateInterface();
            });
        });

        document.querySelectorAll('input[name="phase3"]').forEach(radio => {
            radio.addEventListener('change', (event) => {
                this.connections.phase3 = event.target.value;
                this.updateInterface();
            });
        });

        document.getElementById('threePhaseSwitch').addEventListener('change', (event) => {
            this.threePhaseOn = event.target.checked;
            if (this.threePhaseOn) {
                this.twoPhaseOn = false;
                document.getElementById('twoPhaseSwitch').checked = false;
            }
            this.updateInterface();
        });

        document.getElementById('twoPhaseSwitch').addEventListener('change', (event) => {
            this.twoPhaseOn = event.target.checked;
            if (this.twoPhaseOn) {
                this.threePhaseOn = false;
                document.getElementById('threePhaseSwitch').checked = false;
            }
            this.updateInterface();
        });

        document.getElementById('clearConnections').addEventListener('click', () => {
            this.clearConnections();
        });
    }

    clearConnections() {
        document.querySelectorAll('input[type="radio"]').forEach(radio => {
            radio.checked = false;
        });
        this.connections = { phase1: null, phase2: null, phase3: null };
        this.updateInterface();
    }

    updateInterface() {
        this.updateWires();
        this.updateSwitchStates();
        this.updateRotation();
        this.updateArrow();
        this.updatePhaseMap();
        this.updateStatusText();
    }

    updateWires() {
        Object.keys(this.wireOutputs).forEach((key) => {
            const element = this.wireOutputs[key];
            const phaseValue = this.connections[key];
            element.className = 'phase-wire';
            if (phaseValue) {
                element.classList.add('active', phaseValue);
            }
        });
    }

    updateSwitchStates() {
        this.threePhaseState.textContent = this.threePhaseOn ? 'ON' : 'OFF';
        this.twoPhaseState.textContent = this.twoPhaseOn ? 'ON' : 'OFF';
        this.threePhaseState.className = `switch-state ${this.threePhaseOn ? 'on' : 'off'}`;
        this.twoPhaseState.className = `switch-state ${this.twoPhaseOn ? 'on' : 'off'}`;
    }

    getRotationDirection() {
        const allConnected = this.connections.phase1 && this.connections.phase2 && this.connections.phase3;

        if (!allConnected) {
            return 0;
        }

        if (this.threePhaseOn) {
            if (this.connections.phase1 === 'R' && this.connections.phase2 === 'Y' && this.connections.phase3 === 'B') {
                return 1;
            }
            if (this.connections.phase1 === 'R' && this.connections.phase2 === 'B' && this.connections.phase3 === 'Y') {
                return -1;
            }
            return 0;
        }

        if (this.twoPhaseOn) {
            return -1;
        }

        return 0;
    }

    updateRotation() {
        this.rotationDirection = this.getRotationDirection();
        this.isRotating = this.rotationDirection !== 0;
    }

    updateArrow() {
        this.rotationArrow.className = 'rotation-arrow';
        if (this.isRotating) {
            this.rotationArrow.classList.add('on', this.rotationDirection === 1 ? 'cw' : 'ccw');
        } else {
            this.rotationArrow.classList.add('off');
        }
    }

    updatePhaseMap() {
        this.phaseMap.innerHTML = '';
        Object.keys(this.connections).forEach((key, index) => {
            const phaseValue = this.connections[key] || '-';
            const name = `Phase ${index + 1}`;
            const entry = document.createElement('div');
            const dot = document.createElement('span');
            dot.className = `label ${phaseValue}`;
            dot.textContent = phaseValue === '-' ? '-' : phaseValue;
            const text = document.createElement('span');
            text.textContent = `${name} → ${phaseValue}`;
            entry.appendChild(dot);
            entry.appendChild(text);
            this.phaseMap.appendChild(entry);
        });
    }

    updateStatusText() {
        if (!this.connections.phase1 || !this.connections.phase2 || !this.connections.phase3) {
            this.statusText.textContent = 'Select all three phase connections to begin.';
            return;
        }

        if (this.isRotating) {
            const direction = this.rotationDirection === 1 ? 'Clockwise' : 'Anticlockwise';
            const source = this.threePhaseOn ? '3-Phase supply' : '2-Phase DC supply';
            this.statusText.textContent = `${source} is ON. Machine rotating ${direction}.`;
            return;
        }

        if (this.threePhaseOn || this.twoPhaseOn) {
            this.statusText.textContent = 'Connections are set but the phase sequence does not produce rotation.';
            return;
        }

        this.statusText.textContent = 'Switch ON either 3-Phase or DC supply to rotate the machine.';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new SynchronousMachineSimulation();
});
