import React, { useState } from 'react';
import { ListGroupItem, Button } from 'react-bootstrap';

const SleepPeriod = ({
  sleepPeriod,
  updateSleepPeriod,
  removeSleepPeriod
}) => {
  const [editMode, setEditMode] = useState(false)
  const [editableSleepPeriod, setSleepPeriod] = useState(sleepPeriod)

  return (
    <ListGroupItem>
      { editMode === false && 
        <div>
          <a href={ '/sleepPeriods/' + sleepPeriod.id }>
            Sleep period { sleepPeriod.id }
          </a>
          <p>Start time: { sleepPeriod.startTime }</p>
          <p>End time: { sleepPeriod.endTime }</p>
          <p>Duration: { sleepPeriod.duration }h</p>
          <br />
          <Button onClick={ () => removeSleepPeriod(sleepPeriod) } variant="danger" size="sm">Delete</Button>
          <Button onClick={ () => setEditMode(true) } variant="warning" size="sm">Edit</Button>
        </div>
      }
      { editMode === true && 
        <div>
          <a href={ '/sleepPeriods/' + sleepPeriod.id }>
            Sleep period { sleepPeriod.id }
          </a>
          <p>Start time: 
            <input
              onChange = {
                event => setSleepPeriod({...editableSleepPeriod,
                  startTime: event.target.value
                })
              }
              type='datetime-local'
              id='startTime'
              name='startTime'
              value={ editableSleepPeriod.startTime }
            />
          </p>
          <p>End time: 
            <input
              onChange = {
                event => setSleepPeriod({...editableSleepPeriod,
                  endTime: event.target.value
                })
              }
              type='datetime-local'
              id='endTime'
              name='endTime'
              value={ editableSleepPeriod.endTime }
            />
          </p>
          <p>Duration: { sleepPeriod.duration }h</p>
          <br />
          <Button onClick={ () => removeSleepPeriod(sleepPeriod) }>Delete</Button>
          <Button onClick={ () => {
            updateSleepPeriod(editableSleepPeriod);
            setEditMode(false);
          }}>Save</Button>
        </div>
      }
    </ListGroupItem>
  )
}

export default SleepPeriod