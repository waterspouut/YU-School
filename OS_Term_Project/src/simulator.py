from algorithm_selector import get_algorithm_choice
from input_element import get_frame_size, get_reference_list
from result import report_results
import os

def run_simulation():
    while True:
        algorithm_name, algorithm_func = get_algorithm_choice()
        print(f"\n선택된 알고리즘: {algorithm_name}")
        
        frame_size = get_frame_size()
        reference_list = get_reference_list()
        
        if algorithm_name == "Second Chance":
            final_frames, page_faults, page_hits, second_chance_info, frame_history = algorithm_func(frame_size, reference_list)
            report_results(algorithm_name, final_frames, page_faults, page_hits, reference_list, second_chance_info, frame_history)
        else:
            final_frames, page_faults, page_hits, frame_history = algorithm_func(frame_size, reference_list)
            report_results(algorithm_name, final_frames, page_faults, page_hits, reference_list, None, frame_history)
    
        choice = input("\n프로그램을 종료하려면 qq를 입력하세요") 
        if choice == 'qq':
            break
        else:
            os.system('cls')
            continue