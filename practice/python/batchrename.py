import time, os.path
from string import Template
photofile = ['img_1074.jpg', 'img_1076.jpg', 'img_1077.jpg']
class BatchRename(Template):
    delimiter = '%'

fmt = input('Enter rename style')
t = BatchRename(fmt)
date = time.strftime('%d%b%y')
for i, filename in enumerate(photofile):
    base, ext = os.path.splitext(filename)
    newname = t.substitute(d=date, n=i, f = ext)
    print('{0} --> {1}'.format(filename, newname))

