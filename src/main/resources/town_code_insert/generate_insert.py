import csv

# 입력 파일과 출력 파일 경로 설정
input_file = 'town_code.txt'
output_file = 'insert_queries.sql'

# 확장 가능한 시와 구 리스트 (추후 시와 구를 추가하면 된다)
target_cities = ["서울특별시"]
target_districts = {
    "서울특별시": ["종로구", "중구"]  # 서울특별시 구 리스트
}

# 결과를 저장할 파일 열기
with open(output_file, 'w', encoding='utf-8') as out_file:
    city_codes = {}  # 시 코드 저장
    district_codes = {}  # 구 코드 저장

    # 입력 파일 읽기
    with open(input_file, 'r', encoding='utf-8') as in_file:
        reader = csv.reader(in_file, delimiter='\t')
        next(reader)  # 첫 번째 행(헤더) 건너뛰기

        # 1. 시 코드 처리
        for row in reader:
            code, name, status = row
            name = name.strip()  # 이름 앞뒤 공백 제거
            status = status.strip()  # 상태 앞뒤 공백 제거

            # "존재"인 행만 처리
            if status == "존재" and name in target_cities:
                city_codes[name] = code  # 시 코드 저장
                out_file.write(f"INSERT INTO p_city(code, name) VALUES ({code}, '{name}');\n")
                print(f"✅ {name} 데이터 삽입: {code}, {name}")
                break  # 시 코드는 한 번만 처리되면 되므로 종료

        # 2. 구 코드 처리 (시 코드 기반으로 구 찾기)
        in_file.seek(0)  # 파일 처음으로 돌아가기
        next(reader)  # 첫 번째 행(헤더) 건너뛰기

        for row in reader:
            code, name, status = row
            name = name.strip()  # 이름 앞뒤 공백 제거
            status = status.strip()  # 상태 앞뒤 공백 제거

            # "존재"인 행만 처리
            if status == "존재" and any(name.startswith(city) for city in target_cities):
                for city in target_cities:
                    if name.startswith(city):  # 시가 일치하는 경우
                        for district in target_districts.get(city, []):
                            if name == f"{city} {district}":
                                district_codes[district] = code  # 구 코드 저장
                                out_file.write(f"INSERT INTO p_district(code, name, city_code) VALUES ({code}, '{name}', {city_codes[city]});\n")
                                print(f"✅ {district} 데이터 삽입: {code}, {name}")
        
        # 3. 법정동 처리 (구 코드 기반으로 법정동 찾기)
        in_file.seek(0)  # 파일 처음으로 돌아가기
        next(reader)  # 첫 번째 행(헤더) 건너뛰기

        for row in reader:
            code, name, status = row
            name = name.strip()  # 이름 앞뒤 공백 제거
            status = status.strip()  # 상태 앞뒤 공백 제거

            # "존재"인 행만 처리
            if status == "존재" and any(name.startswith(city) for city in target_cities):
                for city in target_cities:
                    for district, district_code in district_codes.items():
                        district_name = f"{city} {district}"
                        if name != district_name and name.startswith(f"{city} {district}"):
                            out_file.write(f"INSERT INTO p_town(code, name, district_code) VALUES ({code}, '{name}', {district_code});\n")
                            print(f"✅ 법정동 데이터 삽입: {code}, {name}")

    print(f"✅ 쿼리 파일 생성 완료: {output_file}")
