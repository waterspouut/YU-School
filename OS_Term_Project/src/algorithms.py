def fifo(frame_size, reference_string):
    frames = []
    faults = 0
    hits = 0
    frame_history = []

    for page in reference_string:
        if page in frames:
            hits += 1
            frame_history.append((page, frames.copy(), False))
        else:
            faults += 1            
            if len(frames) == frame_size:
                frames.pop(0) 
            frames.append(page)
            frame_history.append((page, frames.copy(), True))
            
    return frames, faults, hits, frame_history

def lru(frame_size, reference_string):
    frames = []
    faults = 0
    hits = 0
    frame_history = []

    for page in reference_string:
        if page in frames:
            hits += 1
            frames.remove(page)
            frames.append(page)
            frame_history.append((page, frames.copy(), False))
        else:
            faults += 1
            if len(frames) == frame_size:
                frames.pop(0) 
            frames.append(page)
            frame_history.append((page, frames.copy(), True))
            
    return frames, faults, hits, frame_history

def second_chance(frame_size, reference_string):
    frames = [] 
    faults = 0
    hits = 0
    second_chance = []
    current_time = 0
    frame_history = []

    for page in reference_string:
        current_time += 1
        page_found_in_frames = False
        for i in range(len(frames)):
            if frames[i][0] == page:
                hits += 1
                frames[i][1] = 1
                page_found_in_frames = True
                frame_history.append((page, [f[0] for f in frames], False))
                break
        
        if not page_found_in_frames:
            faults += 1
            
            if len(frames) < frame_size:
                frames.append([page, 0])
            else:
                while True:
                    if frames[0][1] == 0:
                        frames.pop(0)
                        frames.append([page, 0])
                        break
                    else:
                        temp_page_entry = frames.pop(0)
                        temp_page_entry[1] = 0
                        frames.append(temp_page_entry)
                        second_chance.append((temp_page_entry[0], current_time))
            
            frame_history.append((page, [f[0] for f in frames], True))
            
    final_page_frames = []
    for entry in frames:
        final_page_frames.append(entry[0])
        
    return final_page_frames, faults, hits, second_chance, frame_history