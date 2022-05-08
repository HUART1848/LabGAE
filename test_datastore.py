from os import system

requests = [
    "_kind=book&_key=1234&title=bonsoir&author=michel&type=roman",
    "_kind=book&_key=cornichon&title=cool&author=micheline&type=polar",
    "_kind=book",
    "_kind=book&_key=1234&title=J_ecrase_key_existante&author=imposteur",
    "_kind=animal&_key=340000&name=platypus",
    "name=jenaipasdekind"
]

#target = "http://localhost:8080"
target = "https://labgae-349015.ew.r.appspot.com"
for request in requests:
    full_target = f"\"{target}/datastorewrite?{request}\""
    print(full_target)
    system(f"curl.exe {full_target}")
